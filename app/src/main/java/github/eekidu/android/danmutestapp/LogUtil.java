package github.eekidu.android.danmutestapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LogUtil {
    public static class Builder {

        private Map<String, Object> params;
        int formatLength = 0;

        public Builder() {
            params = new HashMap<>();
        }

        public Builder append(String message) {
//            params.put(message,)
            return this;
        }

        public Builder put(String key, Object value) {
            formatLength = Math.max(key.length(), formatLength);
            params.put(key, value);
            return this;
        }

        @Override
        public String toString() {
            Set<String> keySet = params.keySet();
            StringBuilder stringBuilder = new StringBuilder();
            for (String key : keySet) {
                stringBuilder.append(String.format("%" + formatLength + "s", key))
                        .append(" = ")
                        .append(params.get(key).toString())
                        .append("\n");
            }
            return stringBuilder.toString();
        }

    }
}
