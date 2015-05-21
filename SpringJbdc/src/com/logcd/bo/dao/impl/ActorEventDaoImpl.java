package com.logcd.bo.dao.impl;   
  
import java.sql.Connection;   
import java.sql.PreparedStatement;   
import java.sql.ResultSet;   
import java.sql.SQLException;   
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;   
import java.util.Map;
  
import javax.annotation.Resource;
import javax.sql.DataSource;   
  
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;   
import org.springframework.jdbc.core.PreparedStatementCreator;   
import org.springframework.jdbc.core.RowMapper;   
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;   
import org.springframework.jdbc.support.KeyHolder;   
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
  
import com.logcd.bo.Actor;   
import com.logcd.bo.dao.ActorEventDao;   

public class ActorEventDaoImpl implements ActorEventDao{   
       
    private JdbcTemplate jdbcTemplate;   
       
    //NamedParameterJdbcTemplate��JdbcTemplate��װ��������������������   
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;   
  
    //SimpleJdbcTemplate��JdbcTemplate��װ,ĳЩ����Ҫ��java5���ϲŹ���   
    private SimpleJdbcTemplate simpleJdbcTemplate;   
       
    //�򻯲������ݲ���   
    private SimpleJdbcInsert inserActor;   
       
    public void setDataSource(DataSource dataSource){   
        this.jdbcTemplate = new JdbcTemplate(dataSource);   
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);   
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);   
        this.inserActor = new SimpleJdbcInsert(dataSource)   
        .withTableName("actors")   
        .usingColumns("first_name","last_name")//������Щ�ֶ�   
        .usingGeneratedKeyColumns("id");//�������ɵ�id   
    }   
  
    /**  
     * ��SimpleJdbcInsert����һ����¼  
     */  
    public long inserOneActor(Actor actor){   
        Map<String,Object> parameters = new HashMap<String,Object>();   
        parameters.put("first_name",actor.getFirstName());   
        parameters.put("last_name",actor.getLastName());   
        return inserActor.executeAndReturnKey(parameters).longValue();   
    }   
       
    /**  
     * ͳ��firstName��ͬ������  
     * @param firstName  
     * @return  
     */  
    public int findCountOfActorsByFirstName(String firstName){   
        String sql="select count(0) from actors where first_name = :first_name";   
        SqlParameterSource namedParameters = new MapSqlParameterSource("first_name",firstName);   
        //Map namedParameter = Collections.singletonMap("first_name",firstName);   
        //����һ��Bean��װ�ķ�ʽ   
        //SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(exampleActor);   
        return this.namedParameterJdbcTemplate.queryForInt(sql, namedParameters);   
    }   
  
    /**  
     * ����SQL����  
     * @param sql  
     */  
    public void createTableBySQL(String sql) {   
        this.jdbcTemplate.execute(sql);   
    }   
       
    /**  
     * �����¼�������Զ����ɵ�����Id(MySQL�в��У�Oracle����)  
     * @param ps  
     * @return  
     */  
    public KeyHolder insertActor(final Actor actor){   
        final String addSql = "insert into actors(first_name,last_name) values (?,?)";   
        KeyHolder keyHolder = new GeneratedKeyHolder();   
        this.jdbcTemplate.update(new PreparedStatementCreator(){   
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
                PreparedStatement ps =   
                    conn.prepareStatement(addSql, new String[]{"id"});//����id   
                ps.setString(1, actor.getFirstName());   
                ps.setString(2, actor.getLastName());   
                return ps;   
            }   
               
        });   
        System.out.println(keyHolder.getKey());   
        return keyHolder;   
    }   
       
    /**  
     * ����/����/ɾ������  
     * @param sql �в������  
     * @param obj ����ֵ����  
     */  
    public int operateActor(String sql,Object[] obj){   
        return this.jdbcTemplate.update(sql, obj);   
    }   
  
    /**  
     * ����SQL��ѯ��¼����  
     * @param sql  
     * @return  
     */  
    public int findRowCountBySQL(String sql){   
        return this.jdbcTemplate.queryForInt(sql);   
    }   
       
    /**  
     * ����Id����ָ������  
     * @param id  
     * @return  
     */  
    public Actor findActorById(long id){   
        Actor actor = (Actor) this.jdbcTemplate.queryForObject(   
                "select id,first_name,last_name from actors where id = ?",   
                new Object[]{new Long(id)},    
                new RowMapper(){   
  
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {   
                        Actor act = new Actor();   
                        act.setId(rs.getLong("id"));   
                        act.setFirstName(rs.getString("first_name"));   
                        act.setLastName(rs.getString("last_Name"));   
                        return act;   
                    }   
                       
                });   
        return actor;   
    }   
  
  
    /**  
     * ����Id����ָ������  
     * @param id  
     * @return  
     */  
    public Actor findActorByIdSimple(long id){   
        String sql = "select id,first_name,last_name from actors where id = ?";   
           
        ParameterizedRowMapper<Actor> mapper = new ParameterizedRowMapper<Actor>(){   
            //notice the return type with respect to java 5 covariant return types   
            public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {   
                Actor act = new Actor();   
                act.setId(rs.getLong("id"));   
                act.setFirstName(rs.getString("first_name"));   
                act.setLastName(rs.getString("last_Name"));   
                return act;   
            }   
        };   
           
        return this.simpleJdbcTemplate.queryForObject(sql, mapper, id);   
    }   
       
    /**  
     * �������ж���  
     * @return  
     */  
    public List findAllActors(){   
        return this.jdbcTemplate.query(   
                "select id,first_name,last_name from actors",   
                new ActorMapper());   
    }   
       
    /**  
     * ����һ����̬�ڲ��࣬��Dao�ķ����б�����  
     * @author logcd  
     */  
    private static final class ActorMapper implements RowMapper{   
  
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {   
            Actor act = new Actor();   
            act.setId(rs.getLong("id"));   
            act.setFirstName(rs.getString("first_name"));   
            act.setLastName(rs.getString("last_Name"));   
            return act;   
        }   
           
    }   
   
  
    /**  
     * ��������  
     * @param actors  
     * @return  
     */  
    public int[] updateBatchActors(final List actors){   
        int[] updateCounts =this.jdbcTemplate.batchUpdate(   
                "update actors set first_name = ?, last_name = ? where id =? ",    
                new BatchPreparedStatementSetter(){   
  
                    public int getBatchSize() {   
                        return actors.size();   
                    }   
  
                    public void setValues(PreparedStatement ps, int i) throws SQLException {   
                        ps.setString(1, ((Actor)actors.get(i)).getFirstName());   
                        ps.setString(2, ((Actor)actors.get(i)).getLastName());   
                        ps.setLong(3, ((Actor)actors.get(i)).getId());   
                    }   
                       
                });   
        return updateCounts;   
    }   
  
    /**  
     * ��������  
     * @param actors  
     * @return  
     */  
    public int[] updateBatchActorsSimple(final List<Actor> actors){   
        //�������������ռλ������λ��һһ��Ӧ   
        //SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(actors.toArray());   
        List<Object[]> batch = new ArrayList<Object[]>();   
        for(Actor actor:actors){   
            Object[] values = new Object[]{//ע��˳��   
                    actor.getFirstName(),   
                    actor.getLastName(),   
                    actor.getId()};   
            batch.add(values);   
        }   
        int[] updateCounts = this.simpleJdbcTemplate.batchUpdate(   
                "update actors set first_name = ?, last_name = ? where id =? ",   
                batch);   
        return updateCounts;   
    } 
    
    
    
    @Resource(name = "lobHandler")      
    private LobHandler lobHandler; 
    
    @Resource(name = "incre")         
    private DataFieldMaxValueIncrementer incre;
         
    public void savePost(final Post post) {        
       String sql = " INSERT INTO t_post(post_id,user_id,post_text,post_attach)"     
         + " VALUES(?,?,?,?)";      
       jdbcTemplate.execute(sql,      
         new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {      
             protected void setValues(PreparedStatement ps,      
                  LobCreator lobCreator) throws SQLException {      
                      ps.setInt(1, incre.nextIntValue());       
                      ps.setInt(2, post.getUserId());       
                      lobCreator.setClobAsString(ps, 3, post.getPostText());      
                      lobCreator.setBlobAsBytes(ps, 4, post.getPostAttach());      
            }   
         });      
    }      
         
    public List findAttachs(final int userId){      
       String sql = "SELECT post_id,post_attach FROM t_post where user_id =? and post_attach is not null";      
       return jdbcTemplate.query( sql, new Object[] {userId},      
          new RowMapper() {      
              public Object mapRow(ResultSet rs, int rowNum) throws SQLException {      
              Post post = new Post();      
              int postId = rs.getInt(1);      
              byte[] attach = lobHandler.getBlobAsBytes(rs, 2);      
              post.setPostId(postId);      
              post.setPostAttach(attach);      
              return post;      
          }     
       });     
    }  
}