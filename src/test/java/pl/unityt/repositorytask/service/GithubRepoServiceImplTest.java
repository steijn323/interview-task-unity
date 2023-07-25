package pl.unityt.repositorytask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.unityt.repositorytask.dto.GithubRepositoryResponseDto;
import pl.unityt.repositorytask.dto.RepositoryDetailsDto;
import pl.unityt.repositorytask.exception.GeneralRepositoryWebException;
import pl.unityt.repositorytask.mapper.GithubDtoMapper;
import pl.unityt.repositorytask.service.impl.GithubRepoServiceImpl;

public class GithubRepoServiceImplTest {

    private RestTemplate restTemplate;
    private GithubDtoMapper mapper;
    private GithubRepoServiceImpl githubRepoService;

    @BeforeEach
    void setUp() {
        this.restTemplate = Mockito.mock(RestTemplate.class);
        this.mapper = Mappers.getMapper(GithubDtoMapper.class);
        this.githubRepoService = new GithubRepoServiceImpl(restTemplate, mapper);
    }

    @Test
    public void fetchRepoDetails_correctData_DetailsReturned() {
        String owner = "unity";
        String repositoryName = "reponame";
        String apiUrl = String.format("https://api.github.com/repos/%s/%s", owner, repositoryName);
        GithubRepositoryResponseDto githubResponse = GithubRepositoryResponseDto.builder()
                .fullName("Repo Name")
                .description("Java")
                .stargazersCount(15)
                .build();
        when(restTemplate.getForObject(Mockito.eq(apiUrl), any())).thenReturn(githubResponse);
        RepositoryDetailsDto expectedDto = mapper.mapGithubRepositoryResponseToRepositoryDetails(githubResponse);

        RepositoryDetailsDto actualDto = githubRepoService.fetchRepoDetails(owner, repositoryName);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void fetchRepoDetails_repositoryNotFound_exceptionThrown() {
        String owner = "unityt";
        String repositoryName = "repository-not-found";
        String apiUrl = String.format("https://api.github.com/repos/%s/%s", owner, repositoryName);

        when(restTemplate.getForObject(Mockito.eq(apiUrl), any())).thenReturn(null);

        assertThrows(GeneralRepositoryWebException.class, () -> githubRepoService.fetchRepoDetails(owner, repositoryName));
    }

    @Test
    public void fetchRepoDetails_callFailed_ExceptionThrown() {
        String owner = "unity";
        String repositoryName = "reponame";
        String apiUrl = String.format("https://api.github.com/repos/%s/%s", owner, repositoryName);

        when(restTemplate.getForObject(Mockito.eq(apiUrl), any())).thenThrow(new HttpClientErrorException(HttpStatusCode.valueOf(402)));

        assertThrows(GeneralRepositoryWebException.class, () -> githubRepoService.fetchRepoDetails(owner, repositoryName));
    }
}