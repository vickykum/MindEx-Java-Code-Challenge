package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation read(String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        Compensation compensation = compensationRepository.findByEmployee(employee);
        if(compensation == null){
            throw new RuntimeException("No Compensation found for employee: "+employee);
        }
        return compensation;
    }

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        compensationRepository.insert(compensation);
        return compensation;
    }
}
