package pl.unityt.repositorytask.service;

import pl.unityt.repositorytask.dto.RepositoryDetailsDto;

public interface GithubRepoService {

    RepositoryDetailsDto fetchRepoDetails(String owner, String repositoryName);
}
