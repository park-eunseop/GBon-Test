package kr.co.gb.eunsub.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.gb.eunsub.model.client.ClientInfo;

/**
 * 고객사 정보 repository
 * @author park
 *
 *
 *
 *
 */


@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long>{

}
