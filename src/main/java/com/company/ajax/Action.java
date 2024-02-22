package com.company.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action{
public int execu(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}