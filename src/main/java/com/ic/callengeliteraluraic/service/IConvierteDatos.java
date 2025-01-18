package com.ic.callengeliteraluraic.service;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class <T> clase);

}
