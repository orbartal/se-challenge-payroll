package orbartal.wave.payroll.application.mapper;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultipartFileStreamMapper {

	public InputStream readInputStream(MultipartFile file) {
		try {
			return file.getInputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
