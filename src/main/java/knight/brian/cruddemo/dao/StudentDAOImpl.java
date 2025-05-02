package knight.brian.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import knight.brian.cruddemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    };

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery =
                entityManager.createQuery("FROM Student order by lastName, firstName", Student.class);

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery =
                entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        theQuery.setParameter("theData", lastName);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    };

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    };

    @Override
    @Transactional
    public int deleteAll() {
        int numDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numDeleted;
    };
}
