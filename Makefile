include $(HOME)/.env
include .project.env

all : 	build clean
.PHONY: all

# Build
build :
	./pipeline/scripts/build_artifacts/build.sh "${VERSION}"
.PHONY: build

# clean
clean :
	./pipeline/scripts/clean.sh "${BUILD_TYPE}"