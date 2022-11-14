package com.lawencon.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.elearning.constant.MessageResponse;
import com.lawencon.elearning.dao.StudentDao;
import com.lawencon.elearning.dto.DeleteRes;
import com.lawencon.elearning.dto.InsertDataRes;
import com.lawencon.elearning.dto.InsertRes;
import com.lawencon.elearning.dto.UpdateDataRes;
import com.lawencon.elearning.dto.UpdateRes;
import com.lawencon.elearning.dto.student.StudentData;
import com.lawencon.elearning.dto.student.StudentFindByIdRes;
import com.lawencon.elearning.dto.student.StudentInsertReq;
import com.lawencon.elearning.dto.student.StudentUpdateReq;
import com.lawencon.elearning.model.College;
import com.lawencon.elearning.model.Student;
import com.lawencon.model.SearchQuery;
import com.lawencon.util.DateUtil;

@Service
public class StudentService extends BaseCoreService {

	@Autowired
	private StudentDao studentDao;

	public InsertRes insert(StudentInsertReq data) throws Exception {
		InsertRes result = new InsertRes();
		try {
			Student student = new Student();
			student.setName(data.getName());
			student.setIsActive(data.getIsActive());
			student.setTimeIn(DateUtil.stringToLocalDateTime(data.getTimeInStr()));

			College colleg = new College();
			colleg.setId(data.getCollegeId());
			colleg.setVersion(0);

			student.setCollege(colleg);

			begin();
			Student studentInsert = studentDao.save(student);
			commit();

			InsertDataRes insertDataRes = new InsertDataRes();
			insertDataRes.setId(studentInsert.getId());

			result.setData(insertDataRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public UpdateRes update(StudentUpdateReq data) throws Exception {
		UpdateRes result = new UpdateRes();
		try {
			Student studentDb = studentDao.getById(Student.class, data.getId());
			studentDb.setName(data.getName());
			studentDb.setIsActive(data.getIsActive());
			studentDb.setVersion(data.getVersion());
			studentDb.setTimeIn(DateUtil.stringToLocalDateTime(data.getTimeInStr()));

			begin();
			Student studentUpdate = studentDao.save(studentDb);
			commit();

			UpdateDataRes updateDataRes = new UpdateDataRes();
			updateDataRes.setVersion(studentUpdate.getVersion());

			result.setData(updateDataRes);
			result.setMessage(MessageResponse.SAVED.name());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

	public StudentFindByIdRes findById(String id) throws Exception {
		Student studentDb = studentDao.getById(Student.class, id);

		StudentData data = new StudentData();
		data.setId(studentDb.getId());
		data.setName(studentDb.getName());
		data.setVersion(studentDb.getVersion());

		StudentFindByIdRes result = new StudentFindByIdRes();
		result.setData(data);

		return result;
	}

	public SearchQuery<StudentData> findAll(String textQuery, 
			Integer startPosition, Integer limit) throws Exception {
		SearchQuery<Student> dataDb = studentDao.getAll(Student.class, 
				textQuery, 
				startPosition, limit, 
				"universitas.nama", "nama", "universitas.alamat.nama", "universitas.alamat.kota.nama");

		List<StudentData> students = new ArrayList<>();

		dataDb.getData().forEach(mhs -> {
			StudentData data = new StudentData();
			data.setId(mhs.getId());
			data.setName(mhs.getName());
			data.setVersion(mhs.getVersion());

			students.add(data);
		});

		SearchQuery<StudentData> result = new SearchQuery<>();
		result.setCount(dataDb.getCount());
		result.setData(students);

		return result;
	}

	public DeleteRes deleteById(String id) throws Exception {
		DeleteRes result = new DeleteRes();
		result.setMessage(MessageResponse.FAILED.name());

		try {
			begin();
			boolean isDeleted = studentDao.deleteById(Student.class, id);
			commit();

			if (isDeleted) {
				result.setMessage(MessageResponse.DELETED.name());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return result;
	}

}
