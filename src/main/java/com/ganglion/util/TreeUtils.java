package com.ganglion.util;

import com.ganglion.model.BaseTreeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeUtils<T> {
    /// <summary>
    /// 递归初始化子类型
    /// </summary>
    /// <typeparam name="T"></typeparam>
    /// <param name="source"></param>
    /// <returns></returns>
    public static <T extends BaseTreeInfo> List<T> recursionInitChild(List<T> source) {
        List<T> result = new ArrayList<>();

        List<T> firstParents = source.stream()
                .filter(s -> s.getParentId() == null || s.getParentId().equals(""))
                .collect(Collectors.toList());

        result.addAll(firstParents);

        for (T firstParent :
                firstParents) {
            TreeUtils.recursionParentChild(source, firstParent);
        }

        return result;
    }

    /// <summary>
    /// 持续递归查找子类型
    /// </summary>
    /// <typeparam name="T"></typeparam>
    /// <param name="source"></param>
    /// <param name="parentItem"></param>
    private static <T extends BaseTreeInfo> void recursionParentChild(List<T> source, T parentItem) {
        List<T> childList = source.stream()
                .filter(s -> s.getParentId() != null && s.getParentId().equals(parentItem.getId()))
                .collect(Collectors.toList());

        if (parentItem.getChildren() == null) {
            parentItem.setChildren(new ArrayList<T>());
        }

        if (childList.isEmpty()) {
            return;
        }

        parentItem.getChildren().addAll(childList);

        for (T child :
                childList) {
            TreeUtils.recursionParentChild(source, child);
        }
    }
}
