package pl.unityt.repositorytask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.unityt.repositorytask.dto.RepositoryDetailsDto;
import pl.unityt.repositorytask.service.impl.GithubRepoServiceImpl;

@RestController
@RequestMapping("/repositories")
@RequiredArgsConstructor
public class GithubRepositoryController {

    private final GithubRepoServiceImpl githubRepoService;

    @GetMapping("/{owner}/{repository-name}")
    public RepositoryDetailsDto getRepositoryDetails(@PathVariable("owner") String owner,
                                                     @PathVariable("repository-name") String repositoryName) {
        return githubRepoService.fetchRepoDetails(owner, repositoryName);
    }
}
