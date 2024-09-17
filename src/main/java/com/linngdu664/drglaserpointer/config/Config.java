package com.linngdu664.drglaserpointer.config;

import com.mojang.datafixers.util.Pair;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Config {
    public static final ConcurrentHashMap<Pair<String, ConfigPath>, ArrayList<ConfigValueHolder>> VALUE_HOLDERS = new ConcurrentHashMap();

    public Config(String modId, String configType, ModConfigSpec.Builder builder) {
        Iterator var4 = VALUE_HOLDERS.entrySet().iterator();

        while (true) {
            Map.Entry next;
            Pair s;
            do {
                if (!var4.hasNext()) {
                    return;
                }

                next = (Map.Entry) var4.next();
                s = (Pair) next.getKey();
            } while (!s.getFirst().equals(modId + "/" + configType));

            builder.push(List.of(((ConfigPath)s.getSecond()).strings));
            ArrayList<ConfigValueHolder> h = (ArrayList) next.getValue();

            for (ConfigValueHolder configValueHolder : h) {
                configValueHolder.setConfig(builder);
            }

            builder.pop(((ConfigPath)s.getSecond()).strings.length);
        }
    }

    public static record ConfigPath(String... strings) {
        public ConfigPath(String... strings) {
            this.strings = strings;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                ConfigPath otherPath = (ConfigPath)o;
                return Arrays.equals(this.strings, otherPath.strings);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Arrays.hashCode(this.strings);
        }

        public String[] strings() {
            return this.strings;
        }
    }

    public static class ConfigValueHolder<T> {
        private final BuilderSupplier<T> valueSupplier;
        private ModConfigSpec.ConfigValue<T> config;

        public ConfigValueHolder(String modId, String path, BuilderSupplier<T> valueSupplier) {
            this.valueSupplier = valueSupplier;
            ArrayList<String> entirePath = new ArrayList(List.of(path.split("/")));
            String configType = modId + "/" + entirePath.remove(0);
            VALUE_HOLDERS.computeIfAbsent(Pair.of(configType, new ConfigPath(entirePath.toArray(new String[0]))), (s) -> new ArrayList()).add(this);
        }

        public void setConfig(ModConfigSpec.Builder builder) {
            this.config = this.valueSupplier.createBuilder(builder);
        }

        public void setConfigValue(T t) {
            this.config.set(t);
        }

        public ModConfigSpec.ConfigValue<T> getConfig() {
            return this.config;
        }

        public T getConfigValue() {
            return this.config.get();
        }
    }

    public interface BuilderSupplier<T> {
        ModConfigSpec.ConfigValue<T> createBuilder(ModConfigSpec.Builder var1);
    }
}
