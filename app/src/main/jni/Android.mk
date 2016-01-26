LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NativeLib
LOCAL_SRC_FILES := native.cpp utils.cpp
LOCAL_LDLIBS    := -llog

include $(BUILD_SHARED_LIBRARY)