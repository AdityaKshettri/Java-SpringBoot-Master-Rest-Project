package JavaRestMaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import JavaRestMaster.entity.Basic;
import JavaRestMaster.repository.BasicRepository;

@Service
public class BasicService 
{
	@Autowired
	private BasicRepository basicRepository;
	
	public List<Basic> findAll() {
		return basicRepository.findAll();
	}
	
	public Optional<Basic> findById(int id) {
		return basicRepository.findById(id);
	}
	
	@Transactional
	public Basic save(Basic theBasic) {
		return basicRepository.save(theBasic);
	}
	
	@Transactional
	public void deleteById(int id) {
		basicRepository.deleteById(id);
	}
}
