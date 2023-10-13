SHELL=/bin/bash

# Constants

COLOR_RED=\033[0;31m
COLOR_GREEN=\033[0;32m
COLOR_YELLOW=\033[0;33m
COLOR_BLUE=\033[0;34m
COLOR_NONE=\033[0m
COLOR_CLEAR_LINE=\r\033[K

CLIENT=client
SERVER=server

# Targets

.PHONY: help
help:
	@echo "Targets:"
	@grep -E '^[0-9a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "$(COLOR_BLUE)%s$(COLOR_NONE)|%s\n", $$1, $$2}' \
		| column -t -s '|'

.PHONY: clean
clean: ## Remove all built artefacts
	@echo 'Removing built artefacts'
	@pushd "${CLIENT}"/ \
		&& make clean \
		&& popd
	@pushd "${SERVER}" \
		&& gradle clean \
		&& rm -rf out \
		&& popd

.PHONY: client
client: ## Build the client
	@echo 'Building client'
	@pushd "${CLIENT}"/ \
		&& make dist \
		&& popd

.PHONY: server
server: client ## Build the server
	@echo "Copying ${CLIENT} artefacts"
	@mkdir -p ${SERVER}/src/main/resources/web/
	@cp -r -v ${CLIENT}/build/* ${SERVER}/src/main/resources/web/
	@pushd server/ \
		&& make dist \
		&& popd

.PHONY: dist
dist: client server ## Build a distribution

.PHONY: run
run: dist ## Build and run the distribution
	@java -jar ./server/build/libs/starter.server-all.jar
