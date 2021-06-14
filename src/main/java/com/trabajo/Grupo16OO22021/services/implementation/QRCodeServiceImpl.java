package com.trabajo.Grupo16OO22021.services.implementation;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import com.trabajo.Grupo16OO22021.services.QRCodeService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeServiceImpl implements QRCodeService {

	@Override
	public byte[] generate(String text, int width, int height) {

		try (ByteArrayOutputStream bos = QRCode.from(text).withSize(width, height).stream();) {

			return bos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}