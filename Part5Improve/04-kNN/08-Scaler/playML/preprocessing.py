import numpy as np


class StandardScaler:

    def __init__(self):
        self.mean_ = None
        self.scale_ = None

    def fit(self, X):
        """根据训练数据集获得数据的方差和均值"""
        assert X.ndim == 2, "The dimension of X must be 2"
        # 计算均值,都是按列进行计算
        self.mean_ = np.array([np.mean(X[:, i]) for i in range(X.shape[1])])
        # 计算方差
        self.scale_ = np.array([np.std(X[:, i]) for i in range(X.shape[1])])

        return self

    def transform(self, X):
        """根据训练数据集获得的数据的方差和均值进行归一化"""
        assert X.ndim == 2, "The dimension of X must be 2"
        # 必须是已经fit过地，mean_和scale_都不是None
        assert self.mean_ is not None and self.scale_ is not None, "must fit before transform"
        assert X.shape[1] == len(self.mean_), "the feature number of X must be equal to mean_ and std_"
        resultX = np.empty(shape=X.shape, dtype=float) # 初始化一个和X大小相等的空浮点型矩阵
        # 遍历每一列进行归一化
        for col in range(X.shape[1]):
            resultX = (X[:, col] - self.mean_[col]) / self.scale_ # 均值方差归一化的核心原理
        return resultX
