#!/bin/bash
#
# chkconfig: - 99 99
# description: the zowbman @APP_NAME@ service.
# processname: @APP_NAME@
#
#MAIN_CLASS=TokenMain
#JAVA=/srv/jdk1.7.0_25/bin/java
#mkdir -p work/log
#PATH_LIB=./lib
#CLASSPATH=../etc
#for jar in `ls $PATH_LIB/*.jar`
#do
#  CLASSPATH="$CLASSPATH:""$jar"
#done
#exec -a $MAIN_CLASS $JAVA -server -Xmx2048m -classpath "$CLASSPATH" $MAIN_CLASS $ARGS >> work/log/stdout.log 2>> work/log/stdout.log

# source function library
. /etc/init.d/functions
JAVA=@JAVA@
MAIN_CLASS=@MAIN_CLASS@
APP=@APP_NAME@
APP_DIR=@BASEDIR@/$APP
CLASSPATH=$APP_DIR/etc
WORK_DIR=$APP_DIR/work
LOG_DIR=$WORK_DIR/log
DATA_DIR=$WORK_DIR/data
PID_FILE=$WORK_DIR/pid

#OPTS=" -server "
OPTS=" @JAVA_OPTS@ "
#OPTS="-Dlog.dir=$LOG_DIR -Xms1024m -Xmx2048m -Xmn1600m"
#OPTS="$OPTS -XX:+UseParallelOldGC -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8"
#OPTS=" -server -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -Xloggc:$LOG_DIR/gc.log"
#OPTS="$OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8001 "

RETVAL=0

#

wait_for_pid () {
	try=0
	while test $try -lt 30; do
		case "$1" in
			'created')
			if [ -f "$2" ] && [ "x$(cat $2)" != 'x' ]; then
				try=''
				break
			fi ;;
			'removed')
			if [ ! -f "$2" ] ; then
				try=''
				break
			fi ;;
		esac
		echo -n .
		try=`expr $try + 1`
		sleep 1
	done
}

#
start() 
{
    PID=$(cat $PID_FILE 2>/dev/null)
    if checkpid "$PID"; then
        status -p $PID_FILE $APP
        return 0
    fi 
	
    mkdir -p $LOG_DIR $DATA_DIR

	cd $APP_DIR
    export CLASSPATH="$CLASSPATH:$APP_DIR/lib/*"
    run_cmd="$JAVA $OPTS $MAIN_CLASS"
    $run_cmd >>$LOG_DIR/stdout.log 2>&1 &
    echo $! >"$PID_FILE"

	if [ $? -gt 0 ]; then
		failure
        return 1
	fi

	wait_for_pid created $PID_FILE
	if [ -n "$try" ] ; then
		failure
        return 1
	else
		success && echo "Starting $APP"
	fi
}

#
stop()
{
	PID=$(cat $PID_FILE 2>/dev/null)
	if checkpid "$PID"; then
		kill -KILL $PID && rm $PID_FILE 2>/dev/null
		wait_for_pid removed $PID_FILE
		if [ -n "$try" ] ; then
			failure
			return 1
		else
			success
		fi
	else
		failure
		rm $PID_FILE 2>/dev/null
	fi
	echo "Stopping $APP"
}

restart() 
{
	stop
	if [ $? -ne 0 ]; then
    	return 1
    fi
	start
}

case "$1" in
  start) 
	start ;;
  stop) 
	stop ;;
  restart|force-reload|reload) 
	restart ;;
  status)
	status -p $PID_FILE $APP;;
  *)
    echo $"Usage: $0 {start|stop|status|restart|reload|force-reload}"
    exit 1
esac

RETVAL=$?
exit $RETVAL