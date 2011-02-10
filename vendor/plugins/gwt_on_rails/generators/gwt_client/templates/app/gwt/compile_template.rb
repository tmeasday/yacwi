#!/bin/sh
APPDIR=`dirname $0`;
VENDOR_LIBS="$APPDIR/lib/gwt-rest.jar:$APPDIR/lib/gwtx.jar" # append custom entries here
java -XstartOnFirstThread -cp "$APPDIR/src:$VENDOR_LIBS:$APPDIR/bin:<%= gwt_home %>/gwt-user.jar:<%= gwt_home %>/gwt-dev-<%= gwt_dev_platform %>.jar" com.google.gwt.dev.GWTCompiler -out "<%= RAILS_ROOT %>/public/gwt" "$@" <%= gwt_module %>;