package org.example.services;

import org.springframework.stereotype.Service;
import org.example.repository.TableRepository;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<String> getTableNames() {
        return tableRepository.getTableNames();
    }
}
