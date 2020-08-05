package com.svilen.onlinebookstore.service.impl;

import com.svilen.onlinebookstore.domain.entities.News;
import com.svilen.onlinebookstore.domain.models.service.NewsServiceModel;
import com.svilen.onlinebookstore.error.NewsInvalidNameException;
import com.svilen.onlinebookstore.error.NewsNotFoundException;
import com.svilen.onlinebookstore.repository.NewsRepository;
import com.svilen.onlinebookstore.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper, Validator validator) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public NewsServiceModel addNews(NewsServiceModel newsServiceModel) throws Exception {
        if(!this.validator.validate(newsServiceModel).isEmpty()){
            throw new NewsInvalidNameException("Invalid news name!");
        }

        this.newsRepository
                .saveAndFlush(this.modelMapper
                        .map(newsServiceModel, News.class));

        return newsServiceModel;
    }

    @Override
    public List<NewsServiceModel> findAllNews() {
        return this.newsRepository.findAll().stream().map(n -> this.modelMapper.map(n, NewsServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public NewsServiceModel findNewsById(String id) throws NewsInvalidNameException {
        return this.newsRepository.findById(id).map(n -> this.modelMapper.map(n, NewsServiceModel.class))
                .orElseThrow(() -> new NewsNotFoundException("News with this id was not found"));
    }

    @Override
    public NewsServiceModel editNews(String id, NewsServiceModel newsServiceModel) throws NewsInvalidNameException {
        News news = this.newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException("News with this id was not found"));

        news.setName(newsServiceModel.getName());
        news.setText(newsServiceModel.getText());

        return this.modelMapper.map(this.newsRepository.saveAndFlush(news), NewsServiceModel.class);
    }

    @Override
    public void deleteNews(String id) {
        this.newsRepository.deleteById(id);
    }
}
