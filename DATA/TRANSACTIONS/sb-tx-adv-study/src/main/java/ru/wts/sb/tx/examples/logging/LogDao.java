package ru.wts.sb.tx.examples.logging;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<Log, Integer>, LogDaoCustom { }
