package com.jayus.smallSpring.step18.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * @author : h zk
 * @date : 2023/8/2 15:04
 * @description :
 **/
public interface GenericConverter {

    Set<ConvertiblePair> getConvertibleTypes();

    Object convert(Object source,Class sourceType,Class targetType);

    final class ConvertiblePair{
        private final Class<?> sourceType;

        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "Source type must not be null");
            Assert.notNull(targetType, "Target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class){
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.getSourceType()) && this.targetType.equals(other.targetType);
        }
    }

}
