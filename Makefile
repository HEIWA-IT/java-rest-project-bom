include $(HOME)/.env
include ./pipeline/.project_env

all : 	build clean
.PHONY: all

# Build
build :
	./pipeline/scripts/build_artifacts/build.sh "${VERSION}"-SNAPSHOT

# clean
clean :
	./pipeline/scripts/clean.sh "${BUILD_TYPE}"