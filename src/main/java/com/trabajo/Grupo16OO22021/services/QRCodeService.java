package com.trabajo.Grupo16OO22021.services;

public interface QRCodeService {

  byte[] generate(String text, int width, int height);
}