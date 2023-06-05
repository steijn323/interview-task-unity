package pl.unityt.repositorytask.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.unityt.repositorytask.dto.RepositoryDetailsDto;
import pl.unityt.repositorytask.service.impl.GithubRepoServiceImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GithubRepositoryController.class)
class GithubRepositoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GithubRepoServiceImpl githubRepoService;

    @Test
    public void getRepositoryDetailsTest() throws Exception {
        String owner = "owner";
        String repoName = "repo";
        RepositoryDetailsDto repositoryDetailsDto = RepositoryDetailsDto.builder()
                .fullName("testFullName")
                .description("testDescription")
                .cloneUrl("xedsa")
                .stars(100)
                .createdAt("2022-04-20T18:50:12Z")
                .build();

        when(githubRepoService.fetchRepoDetails(owner, repoName)).thenReturn(repositoryDetailsDto);

        mockMvc.perform(get("/repositories/{owner}/{repository-name}", owner, repoName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(repositoryDetailsDto.getFullName()))
                .andExpect(jsonPath("$.description").value(repositoryDetailsDto.getDescription()))
                .andExpect(jsonPath("$.cloneUrl").value(repositoryDetailsDto.getCloneUrl()))
                .andExpect(jsonPath("$.stars").value(repositoryDetailsDto.getStars()))
                .andExpect(jsonPath("$.createdAt").value(repositoryDetailsDto.getCreatedAt()));
    }
}
