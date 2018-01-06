package com.eks.repository.query;

import com.eks.entity.User;
import com.eks.enums.UserSexualEnum;
import com.eks.vo.base.UserVo;
import com.eks.vo.query.UserQueryVo;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor//全参构造器
public class UserQuerySpecification implements Specification<User> {
    private UserQueryVo queryVO;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> list = new ArrayList<>();
        Predicate recordStatusEq = criteriaBuilder.equal(root.get("recordStatus"),1);
        list.add(recordStatusEq);

        //开始时间、结束时间
        Date startDate = queryVO.getStartDate();
        Date endDate = queryVO.getEndDate();
        if (startDate != null && endDate != null) {
            Predicate predicate = criteriaBuilder.between(root.get("createDate"), startDate,endDate);
            list.add(predicate);
        }else if(startDate != null){
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"),startDate);
            list.add(predicate);
        }else if(endDate != null){
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("createDate"),endDate);
            list.add(predicate);
        }

        UserVo userVo = queryVO.getUserVo();
        if(userVo != null){
            String name = userVo.getName();
            if(name != null && !"".equals(name)){
                Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
                list.add(predicate);
            }
            UserSexualEnum userSexualEnum = userVo.getUserSexualEnum();
            if(userSexualEnum != null){
                Predicate predicate = criteriaBuilder.equal(root.get("userSexualEnum"), userSexualEnum);
                list.add(predicate);
            }
        }

        criteriaQuery.where(list.toArray(new Predicate[list.size()]));
        return null;
    }
}
