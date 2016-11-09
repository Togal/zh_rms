#!/bin/bash
echo "执行 gradlew clean deploy ..."

#执行编译
bash gradlew clean deploy || exit $?

#将编译后文件同步到outer目录
mkdir -p outer/lib
rsync -aq --delete build/deploy/ outer/lib/
rsync -aq --delete build/deploy/webapp/ outer/lib/webapp/

#将配置文件同步到outer/etc目录
mkdir -p outer/etc
rsync -aq --delete build/resources/main/ outer/etc --exclude-from=exclude.list

#将启动脚本同步到outer目录
install -v -c -m 755 run.sh outer/
install -v -c -m 755 dcrun.sh outer/ #docker

#将配置文件模板tpl文件重命名（去掉tpl后缀）
if cd outer/etc; then
    for conf in $(find -type f -name "*.tpl"); do
        mv -v $conf ${conf%.tpl}
    done
fi

exit 0