package dev.skyleaworlder.githelperplus

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.junit.Test
import java.io.File
import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter

class CommitFinderTest {
    private val commitFinder by lazy {
        val repoPath = "D:\\tmp\\Telephony\\.git"
        val builder = FileRepositoryBuilder()
        val repository: Repository = builder
            .setGitDir(File(repoPath))
            .readEnvironment()
            .findGitDir()
            .build()
        CommitFinder(repository)
    }
    private val logOutputPath = "D:\\tmp\\CommitFinderTest.log"
    private val logger = Logger.getLogger("CommitFinderTest")

    init {
        val fhd = FileHandler(logOutputPath)
        logger.addHandler(fhd)
        fhd.formatter = SimpleFormatter()
    }

    @Test
    fun getCommitsBetweenTwoCommitsTest() {
        val newCommitId = "7be6599cbdfecbe0c01438478492deb015846fa8"
        val oldCommitId = "2e349325beda37f33d2c1e29b152cabee4e62661"

        var commitNum = 0
        commitFinder
            .getCommitsBetweenTwoCommits(newCommitId, oldCommitId)
            .forEach { logger.info(it.toString()); commitNum++ }
        logger.info("total commit number: $commitNum")
    }
}