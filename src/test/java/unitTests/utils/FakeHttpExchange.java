package unitTests.utils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpContext;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;

public class FakeHttpExchange extends HttpExchange {

    private final Headers requestHeaders = new Headers();
    private final Headers responseHeaders = new Headers();
    private final ByteArrayOutputStream responseBody = new ByteArrayOutputStream();
    private InputStream requestBody = new ByteArrayInputStream(new byte[0]);
    private int responseCode = 200;
    private URI requestURI = URI.create("/test");

    @Override
    public Headers getRequestHeaders() {
        return requestHeaders;
    }

    @Override
    public Headers getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public URI getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String uri) {
        this.requestURI = URI.create(uri);
    }

    @Override
    public String getRequestMethod() {
        return "GET";
    }

    @Override
    public HttpContext getHttpContext() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void close() {
        // no-op
    }

    @Override
    public InputStream getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String body) {
        this.requestBody = new ByteArrayInputStream(body.getBytes());
    }

    @Override
    public OutputStream getResponseBody() {
        return responseBody;
    }

    public String getResponseBodyAsString() {
        return responseBody.toString();
    }

    @Override
    public void sendResponseHeaders(int rCode, long responseLength) {
        this.responseCode = rCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return new InetSocketAddress("localhost", 12345);
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return new InetSocketAddress("localhost", 8080);
    }

    @Override
    public String getProtocol() {
        return "HTTP/1.1";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        // no-op
    }

    @Override
    public void setStreams(InputStream i, OutputStream o) {
        this.requestBody = i;
        // We ignore output stream override for simplicity
    }

    @Override
    public HttpPrincipal getPrincipal() {
        return null;
    }
}
