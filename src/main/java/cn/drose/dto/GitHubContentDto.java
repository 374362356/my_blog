package cn.drose.dto;

import lombok.Data;

@Data
public class GitHubContentDto {
    private String name;

    private String path;

    private String sha;

    private String size;

    private String html_url;

    private String git_url;

    private String download_url;

    private String type;

}
