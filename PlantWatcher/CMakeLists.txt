cmake_minimum_required(VERSION 3.2)
project(PlantWatcher)

set(ENV{PATH} "/home/piouffb/.rbenv/plugins/ruby-build/bin:/home/piouffb/.rbenv/shims:/home/piouffb/.rbenv/bin:/home/piouffb/anaconda/bin:/usr/local/heroku/bin:/usr/local/go/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games")
set(PLATFORMIO_CMD "/usr/local/bin/platformio")

include_directories("$ENV{HOME}/.platformio/packages/framework-arduinoavr/variants/standard")
include_directories("$ENV{HOME}/.platformio/packages/framework-arduinoavr/cores/arduino")
include_directories("$ENV{HOME}/.platformio/packages/toolchain-atmelavr/avr/include")
include_directories("$ENV{HOME}/.platformio/packages/toolchain-atmelavr/lib/gcc/avr/4.8.1/include-fixed")
include_directories("$ENV{HOME}/.platformio/packages/toolchain-atmelavr/lib/gcc/avr/4.8.1/include")

add_definitions(-DF_CPU=16000000L)
add_definitions(-DARDUINO_ARCH_AVR)
add_definitions(-DARDUINO_AVR_UNO)
add_definitions(-DARDUINO=10606)
add_definitions(-DPLATFORMIO=020601)
add_definitions(-D__AVR_ATmega328P__)

add_custom_target(
    PLATFORMIO_BUILD ALL
    COMMAND ${PLATFORMIO_CMD} -f -c clion run
    WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
)

add_custom_target(
    PLATFORMIO_UPLOAD ALL
    COMMAND ${PLATFORMIO_CMD} -f -c clion run --target upload
    WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
)

add_custom_target(
    PLATFORMIO_CLEAN ALL
    COMMAND ${PLATFORMIO_CMD} -f -c clion run --target clean
    WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
)

#
# To enable code auto-completion, please specify path
# to main source file (*.c, *.cpp) and uncomment line below
#
# add_executable(PlantWatcher src/main_change_me.cpp)
