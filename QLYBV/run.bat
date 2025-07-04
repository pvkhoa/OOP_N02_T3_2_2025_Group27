@echo off
cd /d %~dp0
javac -d out model\Bus.java model\VeXe.java manager\QuanLyDatVe.java ui\AppUI.java
java -cp out ui.AppUI
pause
