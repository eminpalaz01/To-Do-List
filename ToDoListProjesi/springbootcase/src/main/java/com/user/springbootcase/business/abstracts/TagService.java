package com.user.springbootcase.business.abstracts;

import java.util.List;
import com.user.springbootcase.api.dto.TagDto;
import com.user.springbootcase.core.utilities.concretes.DataResult;

public interface TagService {

	DataResult<TagDto> save(TagDto tagDto);

	DataResult<List<TagDto>> findAll();
}
