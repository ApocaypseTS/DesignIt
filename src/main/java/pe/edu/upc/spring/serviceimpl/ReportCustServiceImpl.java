package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.repository.ICustomerRepository;
import pe.edu.upc.spring.service.IReportCustomerService;

public class ReportCustServiceImpl implements IReportCustomerService
{
	@Autowired
	private ICustomerRepository dCustomer;
	

}
