package pl.unityt.repositorytask.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.unityt.repositorytask.dto.GithubRepositoryResponseDto;
import pl.unityt.repositorytask.dto.RepositoryDetailsDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface GithubDtoMapper {

    @Mapping(source = "stargazersCount", target = "stars")
    RepositoryDetailsDto mapGithubRepositoryResponseToRepositoryDetails(GithubRepositoryResponseDto githubRepositoryResponseDto);
}
