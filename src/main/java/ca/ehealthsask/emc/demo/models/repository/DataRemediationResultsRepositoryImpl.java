package ca.ehealthsask.emc.demo.models.repository;

import ca.ehealthsask.emc.demo.models.entity.DataRemediationResults;

import java.util.List;

public class DataRemediationResultsRepositoryImpl implements DataRemediationResultsRepository {
    @Override
    public <S extends DataRemediationResults> S save(S s) {
        return null;
    }

    @Override
    public <S extends DataRemediationResults> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public DataRemediationResults findOne(String s) {
        return null;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public Iterable<DataRemediationResults> findAll() {
        return null;
    }

    @Override
    public Iterable<DataRemediationResults> findAll(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(DataRemediationResults dataRemediationResults) {

    }

    @Override
    public void delete(Iterable<? extends DataRemediationResults> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DataRemediationResults> getResults() {
        return null;
    }
}
