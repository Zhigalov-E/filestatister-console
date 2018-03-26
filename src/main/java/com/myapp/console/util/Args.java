package com.myapp.console.util;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {
	@Parameter
	private List<String> parameters = new ArrayList<>();
	
	@Parameter(names={"--directory", "-d"}, description = "parse from directory")
	private boolean isDir = false;

	@Parameter(names={"--recursive", "-r"}, description = "recursive parse directory")
	private boolean isRecursive = false;

	@Parameter(names={"--path", "-p"}, description = "folders or files")
	private List<String> path;

	@Parameter(names={"--help", "-h"}, description = "show help")
	private boolean isHelp = false;


	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean dir) {
		isDir = dir;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public boolean isRecursive() {
		return isRecursive;
	}

	public void setRecursive(boolean recursive) {
		isRecursive = recursive;
	}

	public boolean isHelp() {
		return isHelp;
	}

	public void setHelp(boolean help) {
		isHelp = help;
	}
}
