package com.warden.common.service;

import com.warden.common.utils.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author yangjiaying
 */
@Slf4j
public abstract class BaseService<Repository extends JpaRepository<E, ID>, E, ID> {

    @Autowired
    protected Repository repository;

    public  <S extends E> E insert(S entity) {
        return this.repository.saveAndFlush(entity);
    }

    public List<E> insert(Iterable<E> entities) {
        return this.repository.saveAll(entities);
    }

    public <S extends E> E update(S entity) {

        return this.repository.findById(getId(entity)).map(targetEntity -> {
            UpdateUtil.copyNonNullProperties(entity, targetEntity);
            return repository.saveAndFlush(targetEntity);
        }).orElseThrow(() -> new AccountException("更新失败：记录不存在"));
    }

    public E updateAllProperty(E entity){
        return repository.saveAndFlush(entity);
    }

    public void delete(String id) {

        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得运行期的泛型类型
        Object o = new Object();
        Class clazz = (Class) ptClass.getActualTypeArguments()[2];
        ID newId;
        if (clazz.getName() == Long.class.getName()) {
            newId = (ID) Long.valueOf(id);
        } else if (clazz.getName() == String.class.getName()) {
            newId = (ID) id;
        } else {
            newId=(ID) Long.valueOf(id);
        }

        System.out.println(id.getClass().getSimpleName());
        System.out.println(newId.getClass().getSimpleName());
        repository.deleteById(newId);
    }

    public Page<E> page(Pageable pageable) {
        return this.repository.findAll(pageable.previousOrFirst());
    }

    public Page<E> page(Pageable pageable, String[] searchs) {
        List<Predicate> predicates = new ArrayList<>();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                for (String search : searchs) {
//                    predicates.add()
//                    getPridicateMethod(search, criteriaBuilder).apply(root.get("topic"),search);
//                    getPridicateMethod(search, criteriaBuilder).apply(root.get("topic"),search,search);
                }
                return criteriaBuilder.and();
            }
        };
        return null;
    }

    public List<E> all() {
        return this.repository.findAll();
    }

    public List<E> all(int start, int end) {
        return this.repository.findAll();
    }

    public List<E> top(int end) {
        return all(0, end);
    }

    public ID getId(E t){

        try{
            Class<? extends Object> tClass = t.getClass();

            //得到所有属性
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    //设置可以访问私有变量
                    fields[0].setAccessible(true);

                    //获取属性的名字
                    String name = fields[0].getName();
                    //将属性名字的首字母大写
                    name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());

                    //整合出 getId() 属性这个方法
                    Method m = tClass.getMethod("get"+name);

                    //调用这个整合出来的get方法，强转成自己需要的类型
                    ID id = (ID) m.invoke(t);
                    return id;
                }
            }
            return null;

        }catch(Exception e){
            log.info("没有这个属性");
            return null;
        }
    }

    private Predicate getPridicate(Root<E> root, String param, CriteriaBuilder criteriaBuilder) {
        switch (param) {
            case "eq":
                return criteriaBuilder.equal(root.get("topic"),param);
            case "between":
                return criteriaBuilder.between(root.get("topi`c"), param, param);
            case "like":
                return criteriaBuilder.like(root.get("topic"), "%" + param + "%");
            default:
                return null;
        }

    }

//    private BiFunction<Expression<String>, String, Predicate> getPridicateMethod(String param, CriteriaBuilder criteriaBuilder) {
//        switch (param) {
//            case "eq":
//                return criteriaBuilder::equal;
//            case "between":
//                return criteriaBuilder::between;
//        }
//    }
}
