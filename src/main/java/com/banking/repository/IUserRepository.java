package com.banking.repository;

import com.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    /**
     * Tìm user theo Username
     *
     * @param name -> Username của user
     * @return 1 user
     */
    Optional<User> findByUsername(String name);


    /**
     DELIMITER $
     create procedure updateUser(
     in i_id bigint,
     in i_user_name nvarchar(50),
     in i_ho_ten nvarchar(20),
     out o_result int)
     begin
     if (select count(*) from user where id = i_id) = 0 then set o_result = 2 ;
     else
     if length(ltrim(i_user_name)) = 0
     || length(ltrim(i_ho_ten)) = 0 then
     set o_result = 1;
     else
     update user set
     username = i_user_name,
     full_name = i_ho_ten
     where id = i_id;
     set o_result = 0;
     end if;
     end if;
     end $
     DELIMITER ;
     */
    @Procedure(name = "updateUser")
    int updateUser(
            @Param("i_id") Integer id,
            @Param("i_user_name") String userName,
            @Param("i_ho_ten") String hoTen
    );

    /**
     DELIMITER $
     create procedure deleteUser(
     in i_id bigint,
     out result varchar(20))
     begin
     if (select count(*) from user where id = i_id) = 0 then set result = "khong tim thay user" ;
     else delete from user where id = i_id;
     set result = "xoa thanh cong";
     end if;
     end $
     DELIMITER ;
     */
    @Procedure(name = "deleteUser")
    String deleteUser(@Param("id") Integer id );

}
