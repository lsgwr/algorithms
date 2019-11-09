import numpy as np
from math import sqrt

from collections import Counter


def kNN_classify(k, X_train, y_train, x):
    assert 1 <= k <= X_train.shape[0], "k must be valid"
    # numpy数组的shape[0]表示数组的行数
    assert X_train.shape[0] == y_train.shape[0], "the size of X_train must be equal to the size of y_train"
    # numpy数组的shape[1]表示数组的列数(在机器学习中，列一般作为特性，比如鸢尾花的花瓣的长、花瓣的宽、茎的高度、叶子形状等)
    assert X_train.shape[1] == x.shape[0], "the feature number of x must be equal to X_train"

    distances = [sqrt(np.sum((x_train - x) ** 2)) for x_train in X_train]
    # 获取最近的几个点
    nearest = np.argsort(distances)
    # 获取最近的几个点在目标向量中对应的目标值(即结果是哪个)
    topK_y = [y_train[i] for i in nearest[:k]]
    votes = Counter(topK_y)

    return votes.most_common(1)[0][0]
