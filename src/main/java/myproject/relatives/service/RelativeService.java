package myproject.relatives.service;

import myproject.base.exception.NotFoundException;
import myproject.relatives.dao.RelativeDAO;
import myproject.relatives.dto.RelativeResponseDTO;
import myproject.relatives.entity.Relative;
import myproject.relatives.mapper.RelativeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RelativeService {
    @Inject
    RelativeDAO relativeDAO;
    @Inject
    RelativeMapper relativeMapper;

    public List<RelativeResponseDTO> getRelatives(Integer limit) {
        if (limit == null) limit = -1;
        return relativeMapper.toRelativeDTOs(relativeDAO.getRelatives(limit));
    }

    public RelativeResponseDTO getRelativeById(Long relativeId) throws NotFoundException {
        Relative relative = relativeDAO.findById(relativeId).orElseThrow(
                () -> new NotFoundException("Relative id cannot be found")
        );
        return relativeMapper.toRelativeDTO(relative);
    }
}
