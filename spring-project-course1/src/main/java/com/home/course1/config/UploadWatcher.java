package com.home.course1.config;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.home.course1.utils.CourseUtils;
import com.home.course1.utils.TriggerJobService;

@Component
public class UploadWatcher {

	private TriggerJobService triggerJobService;
	private final String UPLOAD_DIRECTORY = CourseUtils.getWorkDirSubDirectory("public/upload");
	
	public UploadWatcher(TriggerJobService triggerJobService) {
		this.triggerJobService = triggerJobService;
	}
	
	@EventListener(value = ApplicationStartedEvent.class)
	public void watchAfterApplicationStarted() throws Exception {
		triggerJobFileAlreadyExist();
		monitorForNewFiles();
	}
	
	private void triggerJobFileAlreadyExist() throws Exception {
		FileUtils.listFiles(new File(UPLOAD_DIRECTORY), new String[] {"json"}, false)
			.forEach(file ->  {
				try {
					System.out.println("File : " + file);
					triggerJobService.runJob(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	
	private void monitorForNewFiles() throws Exception {
		FileAlterationObserver observer = new FileAlterationObserver(UPLOAD_DIRECTORY);
		FileAlterationMonitor monitor = new FileAlterationMonitor(5_000);
		observer.addListener(new FileAlterationListenerAdaptor() {
			@Override
			public void onFileCreate(File file) {
				try {
					System.out.println("File : " + file);
					triggerJobService.runJob(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		monitor.addObserver(observer);
		monitor.start();
	}
	
}
