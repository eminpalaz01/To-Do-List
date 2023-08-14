package com.user.springbootcase.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.springbootcase.api.dto.TagDto;
import com.user.springbootcase.business.abstracts.TagService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.dataAccess.TagDao;
import com.user.springbootcase.entities.Tag;

@Service
public class TagManager implements TagService {

	private final TagDao tagDao;
	
	public TagManager(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	@Override
	public DataResult<TagDto> save(TagDto tagDto) {
		// TODO Auto-generated method stub
		Tag tag = new Tag();
		
		tag.setName(tagDto.getName());
		
		Tag savedTag = tagDao.save(tag);
		
		tagDto.setId(savedTag.getId());
		return new DataResult<TagDto>(tagDto, true, "Etiket kaydı başarılı.");
	}

	@Override
	public DataResult<List<TagDto>> findAll() {
		// TODO Auto-generated method stub
		List<TagDto> tagsDto = new ArrayList<TagDto>();

		tagDao.findAll().forEach(tag -> {
			TagDto tagDto = new TagDto();
			tagDto.setId(tag.getId());
			tagDto.setName(tag.getName());

			tagsDto.add(tagDto);
		});
		return new DataResult<List<TagDto>>(tagsDto, true, "Etiketler getirildi.");
	}

}
