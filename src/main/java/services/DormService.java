package services;

import repositories.DormRepository;

public class DormService {

    private DormRepository dormRepository = new DormRepository();

    public long getMaleDormCount() {
        return dormRepository.countMaleDorms();
    }

    public long getFemaleDormCount() {
        return dormRepository.countFemaleDorms();
    }

    public long getTotalDormCount() {
        return getMaleDormCount() + getFemaleDormCount();
    }
}
