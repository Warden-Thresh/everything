package com.warden.common.biz;

import io.micrometer.core.instrument.util.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import static io.lettuce.core.MigrateArgs.Builder.key;

/**
 * @author YangJiaYing
 * @date 2019/05/19
 */
public abstract class BaseBiz<M extends BaseMapper<E>, E> {
    @Autowired
    protected M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    /**
     * 通用插入，插入一个实体对象到数据库，所以字段将参与操作，除非你使用ColumnIgnore注解
     *
     * @param entity
     */
    public void insert(E entity) {
        if (entity != null) {

//            setEntityCreateInfo(entity);
            this.mapper.insert(entity);
        }
    }

    /**
     * saveOrUpdate
     *
     * @param entity
     * @param autDbAssignKey
     */
    public void save(E entity, boolean autDbAssignKey) {
        if (entity != null) {
            Object key = key(entity);
            if (key != null) {
                this.updateTemplateById(entity);
            } else {
                this.insert(entity, autDbAssignKey);
            }
        }
    }


    /**
     * （数据库表有自增主键调用此方法）如果实体对应的有自增主键，插入一个实体到数据库，设置assignKey为true的时候，将会获取此主键
     *
     * @param entity
     * @param autDbAssignKey 是否获取自增主键
     */
    public void insert(E entity, boolean autDbAssignKey) {
        if (entity != null) {
//            setEntityCreateInfo(entity);
            this.mapper.insert(entity, autDbAssignKey);
        }
    }

    /**
     * 插入实体到数据库，对于null值不做处理
     *
     * @param entity
     */
    public void insertTemplate(E entity) {
        if (entity != null) {
//            setEntityCreateInfo(entity);
            this.mapper.insertTemplate(entity);
        }
    }

    /**
     * 如果实体对应的有自增主键，插入实体到数据库，对于null值不做处理,设置assignKey为true的时候，将会获取此主键
     *
     * @param entity
     * @param autDbAssignKey
     */
    public void insertTemplate(E entity, boolean autDbAssignKey) {
        if (entity != null) {
//            setEntityCreateInfo(entity);
            this.mapper.insertTemplate(entity, autDbAssignKey);
        }
    }

    /**
     * 批量插入实体。此方法不会获取自增主键的值，如果需要，建议不适用批量插入，适用
     * <pre>
     * insert(E entity,true);
     * </pre>
     *
     * @param list
     */
    public void insertBatch(List<E> list) {
        for (E e : list) {
//            setEntityCreateInfo(e);
        }
        this.mapper.insertBatch(list);
    }

    /**
     * （数据库表有自增主键调用此方法）如果实体对应的有自增主键，插入实体到数据库，自增主键值放到keyHolder里处理
     *
     * @param entity
     * @return
     */
    public KeyHolder insertReturnKey(E entity) {
        if (entity != null) {
//            setEntityCreateInfo(entity);
            return this.mapper.insertReturnKey(entity);
        }
        return null;
    }

    /**
     * 根据主键更新对象，所以属性都参与更新。也可以使用主键ColumnIgnore来控制更新的时候忽略此字段
     *
     * @param entity
     * @return
     */
    public int updateById(E entity) {
        if (entity != null) {
//            setEntityUpdateInfo(entity);
            return this.mapper.updateById(entity);
        }
        return 0;
    }


    /**
     * 根据主键更新对象，只有不为null的属性参与更新
     *
     * @param entity
     * @return
     */
    public int updateTemplateById(E entity) {
        if (entity != null) {
//            setEntityUpdateInfo(entity);
            return this.mapper.updateTemplateById(entity);
        }
        return 0;
    }

    /**
     * 根据主键删除对象，如果对象是复合主键，传入对象本生即可
     *
     * @param key
     * @return
     */
    public int deleteById(Object key) {
        return this.mapper.deleteById(key);
    }


    /**
     * 根据主键获取对象，如果对象不存在，则会抛出一个Runtime异常
     *
     * @param key
     * @return
     */
    public E unique(Object key) {
        return this.mapper.unique(key);
    }

    /**
     * 根据主键获取对象，如果对象不存在，返回null
     *
     * @param key
     * @return
     */
    public E single(Object key) {
        return this.mapper.single(key);
    }


    /**
     * 根据主键获取对象，如果在事物中执行会添加数据库行级锁(select * from table where id = ? for update)，如果对象不存在，返回null
     *
     * @param key
     * @return
     */
    public E lock(Object key) {
        return this.mapper.lock(key);
    }

    /**
     * 返回实体对应的所有数据库记录
     *
     * @return
     */
    public List<E> all() {
        return this.mapper.all();
    }

    /**
     * 返回实体对应的一个范围的记录
     *
     * @param start
     * @param size
     * @return
     */
    public List<E> all(int start, int size) {
        return this.mapper.all(start, size);
    }

    /**
     * 返回实体在数据库里的总数
     *
     * @return
     */
    public long allCount() {
        return this.mapper.allCount();
    }

    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     *
     * @param entity
     * @return
     */
    public List<E> template(E entity) {
        return this.mapper.template(entity);
    }


    /**
     * 模板查询，返回一条结果,如果没有，返回null
     *
     * @param entity
     * @return
     */
    public <E> E templateOne(E entity) {
        return this.mapper.templateOne(entity);
    }

    public List<E> template(E entity, int start, int size) {
        return this.mapper.template(entity, start, size);
    }

    public void templatePage(PageQuery<E> query) {
        this.mapper.templatePage(query);
    }

    /**
     * 符合模板得个数
     *
     * @param entity
     * @return
     */
    public long templateCount(E entity) {
        return this.mapper.templateCount(entity);
    }


    /**
     * 执行一个jdbc sql模板查询
     *
     * @param sql
     * @param args
     * @return
     */
    public List<E> execute(String sql, Object... args) {
        return this.mapper.execute(sql, args);
    }

    /**
     * 执行一个更新的jdbc sql
     *
     * @param sql
     * @param args
     * @return
     */
    public int executeUpdate(String sql, Object... args) {
        return this.mapper.executeUpdate(sql, args);
    }

    public SQLManager getSQLManager() {
        return this.mapper.getSQLManager();
    }

    /**
     * 设置实体新增信息
     *
     * @param entity
     */
//    protected void setEntityCreateInfo(E entity) {
//
//        ReflectionUtils.invokeSetter(entity, "createTime", Calendar.getInstance().getTimeInMillis());
//
//        Class clazz=entity.getClass();
//
//        if(TenantLevel.class.isAssignableFrom(clazz)){
//            String tenantInfoId=BaseContextHandler.getTenantInfoId();
//            if(StringUtils.isNotEmpty(tenantInfoId)){
//                ((TenantLevel)entity).setTenantInfoId(Long.parseLong(tenantInfoId));
//
//            }
//        }
//            }

//    public static void main(String[] args) {
//        System.out.println(TenantLevel.class.isAssignableFrom(BaseBiz.class));
//    }

//    protected Object key(E entity) {
//        return ReflectionUtils.invokeGetter(entity, "id");
//    }

//    /**
//     * 设置实体更新的信息
//     *
//     * @param entity
//     */
//    protected void setEntityUpdateInfo(E entity) {
//        ReflectionUtils.invokeSetter(entity, "updateTime", Calendar.getInstance().getTimeInMillis());
//    }

//    /**
//     * 根据 searchable进行单表查询
//     *
//     * @param searchable
//     * @return
//     */
//    public PageQuery<E> page(Searchable searchable) {
//        PageQuery pageQuery = searchable.getPage();
//        if (pageQuery == null) {
//            pageQuery = new PageQuery();
//        }
//
//        Query<E> query = getSQLManager().query(getEntityClazz());
//        query = SearchCallback.DEFAULT.prepareQuery(query, searchable);
//
//        long totalCount = query.count();
//
//        if (totalCount > 0) {
//            query = SearchCallback.DEFAULT.preparePage(query, searchable);
//            query=SearchCallback.DEFAULT.prepareQuery(query,searchable);
//            query = SearchCallback.DEFAULT.preparOrder(query, searchable);
//            List<E> list = query.select();
//            pageQuery.setList(list);
//            pageQuery.setTotalRow(totalCount);
//        } else {
//            pageQuery.setList(null);
//            pageQuery.setTotalRow(0);
//        }
//
//        return pageQuery;
//    }





//    /**
//     * 根据 searchable进行单表查询
//     *
//     * @param searchable
//     * @return
//     */
//    public List<E> list(Searchable searchable) {
//
//        searchable.removePageable();
//        Query<E> query = getSQLManager().query(getEntityClazz());
//        query = SearchCallback.DEFAULT.prepareQuery(query, searchable);
//        query = SearchCallback.DEFAULT.preparOrder(query, searchable);
//        List<E> list = query.select();
//
//        return list;
//    }


    /**
     * 获取该业务层的泛型实际类
     *
     * @return
     */
    public Class<E> getEntityClazz() {
        Type type = getClass().getGenericSuperclass();
        Type[] generics = ((ParameterizedType) type).getActualTypeArguments();
        Class<E> clazz = (Class<E>) generics[1];
        return clazz;
    }

}
