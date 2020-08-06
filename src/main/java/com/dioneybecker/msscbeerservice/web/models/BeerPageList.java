package com.dioneybecker.msscbeerservice.web.models;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * BeerPageList
 */
public class BeerPageList extends PageImpl<BeerDto> {

    private static final long serialVersionUID = 3633050724876812817L;

    public BeerPageList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
    }
    
    public BeerPageList(List<BeerDto> content) {
		super(content);
	}


}