package com.JCaptcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCaptchaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
		String captchaId = httpServletRequest.getSession().getId();
		BufferedImage challenge = CaptchaServiceSingleton.getInstance()
		.getImageChallengeForID(captchaId,
		httpServletRequest.getLocale());

		// a jpeg encoder
		JPEGImageEncoder jpegEncoder = JPEGCodec
		.createJPEGEncoder(jpegOutputStream);
		jpegEncoder.encode(challenge);
		} catch (IllegalArgumentException e) {
		httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		return;
		} catch (CaptchaServiceException e) {
		httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return;
		}

		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse
		.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}
