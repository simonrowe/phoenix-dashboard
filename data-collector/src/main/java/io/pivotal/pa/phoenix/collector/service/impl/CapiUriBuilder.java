package io.pivotal.pa.phoenix.collector.service.impl;

import org.springframework.beans.factory.annotation.Value;

public class CapiUriBuilder {

    @Value("${default.builder.pageSize}")
    private Integer rowsPerPage;

    @Value("${capi.uri}")
    private String capiUri;

    private String processUriPath;

    public CapiUriBuilder(String processUriPath) {
        this.processUriPath = processUriPath;
    }

    public String build() {
        return String.format("%s%s?page=1&per_page=%s", capiUri, processUriPath, rowsPerPage);
    }
}
