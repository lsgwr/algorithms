import numpy as np


def train_test_split(X, y, test_ratio=0.2, seed=None):
    """将数据X和y按照test_ratio(测试数据占总数据的比例)分割成X_train, X_test, y_train, y_test"""
    # 行数必须相等
    assert X.shape[0] == y.shape[0], "the size of X must be equal to the size of y"
    assert 0.0 <= test_ratio <= 1.0, "test_ratio must be in (0, 1.0]"

    # 设置随机种子，保证多次的随机结果相同
    if seed:
        np.random.seed(seed)

    # 直接对X和y进行shuffle会导致X和y不再同步。既然X和y的行数是一样的，那么更妙的解决方案第对索引进行shuffle
    shuffle_indexes = np.random.permutation(len(X))  # 元素个数来作为随机索引的个数

    test_size = int(len(X) * test_ratio)
    test_indexes = shuffle_indexes[:test_size]  # 取样本数据的前test_size个作为测试数据集
    train_indexes = shuffle_indexes[test_size:]  # test_size后面的数据作为训练数据集

    # Fancy_indexes 获取训练数据集
    X_train = X[train_indexes]
    y_train = y[train_indexes]
    # Fancy_indexes 获取测试数据集
    X_test = X[test_indexes]
    y_test = y[test_indexes]

    return X_train, y_train, X_test, y_test
