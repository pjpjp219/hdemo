package com.nixx._001;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author nixx
 */
public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
