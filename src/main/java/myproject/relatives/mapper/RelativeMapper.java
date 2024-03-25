package myproject.relatives.mapper;

import myproject.relatives.dto.RelativeRequestDTO;
import myproject.relatives.dto.RelativeResponseDTO;
import myproject.relatives.entity.Relative;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "cdi"
)
public interface RelativeMapper {
    RelativeResponseDTO toRelativeDTO(Relative relative);
    List<RelativeResponseDTO> toRelativeDTOs(List<Relative> relatives);

    Relative toRelative(RelativeRequestDTO relativeRequestDTO);
}
