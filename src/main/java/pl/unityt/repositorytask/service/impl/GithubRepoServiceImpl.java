package pl.unityt.repositorytask.service.impl;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.unityt.repositorytask.dto.GithubRepositoryResponseDto;
import pl.unityt.repositorytask.dto.RepositoryDetailsDto;
import pl.unityt.repositorytask.exception.GeneralRepositoryWebException;
import pl.unityt.repositorytask.mapper.GithubDtoMapper;
import pl.unityt.repositorytask.service.GithubRepoService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GithubRepoServiceImpl implements GithubRepoService {
    private final RestTemplate restTemplate;
    private final GithubDtoMapper githubDtoMapper;

    @Override
    public RepositoryDetailsDto fetchRepoDetails(String owner, String repositoryName) {
        if (StringUtils.isBlank(owner) || StringUtils.isBlank(repositoryName)) {
            throw new GeneralRepositoryWebException("Invalid parameters. Owner or Repository Name can not be empty.", HttpStatus.BAD_REQUEST);
        }
        try {
            var response = Optional.ofNullable(restTemplate.getForObject(buildGithubApiUrl(owner, repositoryName), GithubRepositoryResponseDto.class));
            if (response.isEmpty()) {
                throw new GeneralRepositoryWebException("Repository not found", HttpStatus.NOT_FOUND);
            }
            return githubDtoMapper.mapGithubRepositoryResponseToRepositoryDetails(response.get());
        } catch (HttpClientErrorException ex) {
            throw new GeneralRepositoryWebException("Error while fetching repository details", HttpStatus.resolve(ex.getStatusCode().value()));
        }
    }

    private static String buildGithubApiUrl(String owner, String repositoryName) {
        return String.format("https://api.github.com/repos/%s/%s", owner, repositoryName);
    }
}
